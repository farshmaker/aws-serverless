DaFuq
==============================================

What's Here
-----------

This sample includes:

* README.md - this file
* pom.xml - this file is the Maven Project Object Model for the web service
* src/ - this directory contains your Java service source files
* dafuq.template.yml - this file contains the Serverless Application Model (SAM) used
  by AWS Cloudformation to create DynamoDB tables and deploy your application to AWS Lambda and Amazon API
  Gateway. 


What Do I Do Next?
------------------

* Sign in to the AWS Management Console and open the Amazon S3 console at https://console.aws.amazon.com/s3/
* In the Bucket name list, create or choose(if already exist) the "dafuq-jars" bucket. Click "Upload" button and choose aws-demo.jar package under 
"target/" folder (You have to build it before if absent) and click "Upload" again.
* Open https://console.aws.amazon.com/cloudformation/. Create or update existing stack. Then click "Upload a template to Amazon S3" > 
choose dafuq.template.yaml file under project root folder > Enter stack name if you create a new one. Click Next > 
Next > Click "Create change set" > Execute.
* https://console.aws.amazon.com/apigateway/ — list of API. 
To invoke API call https://{my-api-id}.execute-api.{region-id}.amazonaws.com/{stage-name}/{resourcePath}. 
For DaFuq demo example call https://qckewe8f2k.execute-api.us-east-1.amazonaws.com/Prod/{resourcePath}. Resource path according 
https://docs.google.com/document/d/1Sx3AqhHUb3C0_nV98jd2ByPVQSdWRNAbEe4UAZbtuXU
* https://console.aws.amazon.com/dynamodb/ — dynamoDB

