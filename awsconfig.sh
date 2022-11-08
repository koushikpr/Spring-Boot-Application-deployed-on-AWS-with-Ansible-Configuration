#!/bin/bash
yum install git -y
sudo yum install python3 -y
sudo amazon-linux-extras install ansible2 -y
echo "==================================================="
echo "RUN below command manually"
echo "==================================================="
echo python3 -m venv ansible
echo source ~/Patient-details-using-REST-API-/ansible/bin/activate
echo pip install pip --upgrade
echo pip install boto
echo pip install boto3
echo pip install ansible
echo aws configure 
echo ansible-playbook  -i inventory autorunning.yaml
echo "==================================================="
