#!/bin/bash

sed -i s/"BUILD_NUMBER"/"$BUILD_NUMBER"/g app-deployment.yaml
sed -i s/"command: aws"/"command: /usr/local/bin/aws"/g .kube/config
