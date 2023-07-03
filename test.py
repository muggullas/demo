#!/usr/bin/python3

"""
    Note: Requires Python 3.3 or higher
    Author: Giri Srinivas Elluri, Venkata Revanth B
    Version: v2.0.0
    Date last modified: 06.04.2022
    Status: Production
    Email: giri-srinivas.elluri.external@telefonica.com, venkata-revanth.b.external@telefonica.com
"""

import sys
import boto3
import requests
import getpass
import configparser
import base64
import xml.etree.ElementTree as ET
import re
from bs4 import BeautifulSoup
from os.path import expanduser
from time import sleep
from datetime import datetime as dt


##########################################################################
# Purpose
#
# * This script allows to get an sts token for an selected role in
#   an delected aws-account of the telefonica aws landingzone
#
# * This sts-token is stored in your local ~/.aws/credentials file
#   to be used by command line tools
#
# * This script is provided as is, in the hope that it is helpfull,
#   but it is not designed for your specific purpose and there is no
#   garantie, that it works in your specific environment
#
# * Please, adapt this script to your needs yourself.
#

##########################################################################
# Variables

# region: The default AWS region that this script will connect
# to for all API calls
region = 'eu-central-1'

# output format: The AWS CLI output format that will be configured in the
# saml profile (affects subsequent CLI calls)
outputformat = 'json'

# awsconfigfile: The file where this script will store the temp
# credentials under the saml profile
# the directory will be extended by the home directory ~
awsconfigfile = '/.aws/credentials'

# SSL certificate verification: Whether or not strict certificate
# verification is done, False should only be used for dev/test
sslverification = True

# idpentryurl: The initial URL that starts the authentication process.
idpentryurl = 'https://websso-aws.de.pri.o2.com/SecureAuth27/'

##########################################################################

# Get the federated credentials from the user
print("Username:", end=' ')
username = input()
password = getpass.getpass()
print('')

# Initiate session handler
session = requests.Session()


selectedroleindex = sessionreloadflag = 0

# session reload intervel in minutes
sessionreloadintervel = 55

#session reload count 
sessionreloadcount = 6


def connect_to_websso():
    # Programatically get the SAML assertion
    formresponse = session.get(idpentryurl, verify=sslverification)
    idpauthformsubmiturl = formresponse.url

    ##########################################################################
    # TEF SecureAuth Startpage with Username + Password
    ##########################################################################

    # Parse the response and extract all the necessary values
    # in order to build a dictionary of all of the form values the IdP expects
    formsoup = BeautifulSoup(formresponse.text, "html.parser")

    payload = {}

    for inputtag in formsoup.find_all(re.compile('(INPUT|input)')):
        name = inputtag.get('name', '')
        value = inputtag.get('value', '')

        if 'ctl00$ContentPlaceHolder1$MFALoginControl1$UserIDView$ctl00$'\
                'ContentPlaceHolder1_MFALoginControl1_UserIDView_'\
                'txtUserid' in name:
            # Make an educated guess that this is the right field for the username
            payload[name] = username
        elif 'ctl00$ContentPlaceHolder1$MFALoginControl1$UserIDView$ctl00$'\
                'ContentPlaceHolder1_MFALoginControl1_UserIDView_'\
                'tbxPassword' in name:
            # Make an educated guess that this is the right field for the password
            payload[name] = password
        elif 'ctl00$ContentPlaceHolder1$MFALoginControl1$UserIDView$ctl00$'\
                'ContentPlaceHolder1_MFALoginControl1_UserIDView_'\
                'btnDisabled' in name:
            # Skip btnDisabled because of inexplicable redirects
            pass
        else:
            # Simply populate the parameter with the existing value
            # (picks up hidden fields in the login form)
            payload[name] = value

    # Debug the parameter payload if needed
    # Use with caution since this will print sensitive output to the screen
    # print("Payload: ", payload)
    # print("idpauthURL: ", idpauthformsubmiturl)
    # Performs the submission of the IdP login form with the above post data

    response = session.post(
        idpauthformsubmiturl,
        data=payload,
        verify=sslverification,
        allow_redirects=True
    )

    # Debug the response if needed
    # print(response.text)

    ##########################################################################
    # TEF SecureAuth MFA OTP Mobile App choose page with
    # preselected radio button OTP Mobile App
    ##########################################################################
    formsoup = BeautifulSoup(response.text, "html.parser")

    payload = {}
    MFALoginFound = False
    try:
        for inputtag in formsoup.find_all(re.compile('(INPUT|input)')):
            name = inputtag.get('name', '')
            value = inputtag.get('value', '')

            if 'ctl00$ContentPlaceHolder1$MFALoginControl1$RegistrationMethodView'\
                '$ctl00$ContentPlaceHolder1_MFALoginControl1_RegistrationMethodVi'\
                    'ew_btnSubmit' in name:
                # Make an educated guess that it's the right field for the username
                # print 'found submit button'
                MFALoginFound = True
                payload[name] = 'Submit'
            else:
                # Simply populate the parameter with the existing value
                # (picks up hidden fields in the login form)
                payload[name] = value
        if not MFALoginFound:
            raise ValueError('Username and/or password wrong')
    except ValueError as e:
        print(e)
        sys.exit(1)
    # Debug the parameter payload if needed
    # Use with caution since this will print sensitive output to the screen
    # print(payload)

    # Performs the submission of the IdP login form with the above post data
    response = session.post(idpentryurl, data=payload, verify=sslverification)

    # Debug the response if needed
    # print(response.text)

    ##########################################################################
    # TEF SecureAuth MFA Token Page
    ##########################################################################

    print("Please enter MFA-Token:")
    mfa_token = input()
    print('')

    formsoup = BeautifulSoup(response.text, "html.parser")

    payload = {}

    for inputtag in formsoup.find_all(re.compile('(INPUT|input)')):
        name = inputtag.get('name', '')
        value = inputtag.get('value', '')

        if 'ctl00$ContentPlaceHolder1$MFALoginControl1$BrowserRegistrationPasswor'\
            'dView$ctl00$ContentPlaceHolder1_MFALoginControl1_BrowserRegistratio'\
                'nPasswordView_tbxPassword' in name:
            # Make an educated guess that this is the right field for the username
            #print('found MFA Input Field')
            payload[name] = mfa_token
        elif 'ctl00$ContentPlaceHolder1$MFALoginControl1$BrowserRegistrationPass'\
            'wordView$ctl00$ContentPlaceHolder1_MFALoginControl1_Browser'\
                'RegistrationPasswordView_btnAlternative' in name:
            # Skip btnAlternative because of inexplicable redirects
            pass
        else:
            # Simply populate the parameter with the existing value
            # (picks up hidden fields in the login form)
            payload[name] = value

    # Debug the parameter payload if needed
    # Use with caution since this will print sensitive output to the screen
    # print(payload)

    # Performs the submission of the IdP login form with the above post data
    response = session.post(idpentryurl, data=payload, verify=sslverification)
    #print("session cookies: ",session.cookies)
    return response, session.cookies

def delete_websso_credentials():
    # Overwrite and delete the credential variables, just for safety
    username = '##############################################'
    password = '##############################################'
    del username
    del password

def extract_saml_access(response):
    # Decode the response and extract the SAML assertion
    soup = BeautifulSoup(response.text, features="html.parser")
    assertion = ''

    # Look for the SAMLResponse attribute of the input tag (determined by
    # analyzing the debug print lines above)
    try:
        for inputtag in soup.find_all('input'):
            # Debug only: print('inputtag.name = '+ str(inputtag.get('name')))
            if(inputtag.get('name') == 'SAMLResponse'):
                # Deug only: print('Found SAMLResponse')
                assertion = inputtag.get('value')
        if(assertion == ''):
            raise ValueError(
                'Response did not contain a valid SAML assertion! Wrong MFA?'
            )
    except ValueError as e:
        print(e)
        sys.exit(1)

    # Debug only
    # print(base64.b64decode(assertion))

    # Parse the returned assertion and extract the authorized roles
    awsroles = []
    root = ET.fromstring(base64.b64decode(assertion))
    saml_assertion_attribute = '{urn:oasis:names:tc:SAML:2.0:assertion}Attribute'
    saml_assertion_attribute_value = '{urn:oasis:names:tc:SAML:2.0:assertion}'\
        'AttributeValue'

    for saml2attribute in root.iter(saml_assertion_attribute):
        if (saml2attribute.get('Name') ==
                'https://aws.amazon.com/SAML/Attributes/Role'):
            for saml2attributevalue in (
                    saml2attribute.iter(saml_assertion_attribute_value)):
                awsroles.append(saml2attributevalue.text)

    # Note the format of the attribute value should be role_arn,principal_arn
    # but lots of blogs list it as principal_arn,role_arn so let's reverse
    # them if needed
    for awsrole in awsroles:
        chunks = awsrole.split(',')
        if'saml-provider' in chunks[0]:
            newawsrole = chunks[1] + ',' + chunks[0]
            index = awsroles.index(awsrole)
            awsroles.insert(index, newawsrole)
            awsroles.remove(awsrole)

    if sessionreloadflag == 0:
        # If I have more than one role, ask the user which one they want,
        # otherwise just proceed
        print("")
        if len(awsroles) > 1:
            i = 0
            print("Please choose the role you would like to assume:")
            for awsrole in awsroles:
                print('[', i, ']: ', awsrole.split(',')[0])
                i += 1

            print("Selection: ", end=' ')
            global selectedroleindex
            selectedroleindex = input()

            # Basic sanity check of input
            if int(selectedroleindex) > (len(awsroles) - 1):
                print('You selected an invalid role index, please try again')
                sys.exit(0)

            role_arn = awsroles[int(selectedroleindex)].split(',')[0]
            principal_arn = awsroles[int(selectedroleindex)].split(',')[1]
        else:
            role_arn = awsroles[0].split(',')[0]
            principal_arn = awsroles[0].split(',')[1]
    else:
        role_arn = awsroles[int(selectedroleindex)].split(',')[0]
        principal_arn = awsroles[int(selectedroleindex)].split(',')[1]
    return role_arn, principal_arn, assertion

def sts_assume_role(role_arn, principal_arn, assertion):
    # Use the assertion to get an AWS STS token using Assume Role with SAML
    try:
        conn = boto3.client('sts')
        token = conn.assume_role_with_saml(
            RoleArn=role_arn,
            PrincipalArn=principal_arn,
            SAMLAssertion=assertion
        )
        return token
    except Exception as e:
        print('Cannot get valid STS token.')
        print(e)
        sys.exit(1)

def write_aws_credentials_to_file(token):
    # Write the AWS STS token into the AWS credential file
    home = expanduser("~")
    filename = home + awsconfigfile

    # Read in the existing config file
    config = configparser.RawConfigParser()
    config.read(filename)

    # Put the credentials into a saml specific section instead of clobbering
    # the default credentials
    if not config.has_section('saml'):
        config.add_section('saml')

    config.set('saml', 'output', outputformat)
    config.set('saml', 'region', region)
    config.set('saml', 'aws_access_key_id', token['Credentials']['AccessKeyId'])
    config.set('saml', 'aws_secret_access_key',
            token['Credentials']['SecretAccessKey']
            )
    config.set('saml', 'aws_session_token', token['Credentials']['SessionToken'])

    # Write the updated config file
    with open(filename, 'w+') as configfile:
        config.write(configfile)

    # Give the user some basic info as to what has just happened
    print('\n\n----------------------------------------------------------------')
    print('Your new access key pair has been stored in the AWS configuration file'
        '{0} under the saml profile.'.format(filename))
    print('Note that it will expire at '
        '{0}'.format(token['Credentials']['Expiration']))
    print('After this time, you may safely rerun this script to'
        'refresh your access key pair.')
    print('To use this credential, call the AWS CLI with the --profile option'
        '(e.g. aws --profile saml ec2 describe-instances).')
    print('----------------------------------------------------------------\n\n')

def test_aws_credentials(token):
    # Use the AWS STS token to list all of the S3 buckets
    session = boto3.Session(
        aws_access_key_id=token['Credentials']['AccessKeyId'],
        aws_secret_access_key=token['Credentials']['SecretAccessKey'],
        aws_session_token=token['Credentials']['SessionToken']
    )
    s3conn = session.client('s3')

    buckets = s3conn.list_buckets()

    print('Simple API example listing all S3 buckets:')
    print(buckets)

def reload_session_keys():
    response = session.post(
                "https://websso-aws.de.pri.o2.com/SecureAuth27/Authorized/SAML20IdPInit.aspx",
                verify=sslverification,
                cookies=scookies,
                allow_redirects=True
            )
    return response


if __name__ == '__main__':
    response, scookies = connect_to_websso()
    delete_websso_credentials()
    for i in range(sessionreloadcount):
        if sessionreloadflag == 1:
            response = reload_session_keys()
        role_arn, principal_arn, assertion = extract_saml_access(response)
        token = sts_assume_role(role_arn, principal_arn, assertion)
        write_aws_credentials_to_file(token)
        test_aws_credentials(token)
        print("\n----------------------------------------------------------------")
        # print ("keys: ",token)
        print("\nSession initiated at",dt.now())
        if i < sessionreloadcount-1:
            print("Credentials will be automatically refreshed in "+str(sessionreloadintervel)+" Minutes.")
        else:
            print("Credentais will expire after 1 hour")
            sys.exit(1)
        print("----------------------------------------------------------------\n")
        sessionreloadflag = 1
        sleep(60*sessionreloadintervel)  
