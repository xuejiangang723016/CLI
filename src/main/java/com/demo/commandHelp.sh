#@header-start
#@name commandHelp
#@desc ��ȡ�������
#@input {name:"commandName",default:"",type:"String",option:"yes",detail:"��������"}
#@output {name:"closedLink",file:"closedLink"}
#@auth {authcode:"test2|MEQCIJewjdA1WVD7p/CdM10zDan4JYglVaI+2DcunFmvUMgOAiC1lAhpg64cRWSbD063sPpY3hA3p15EFD+E1cjhWSnzhw==",alg:"SM3withSM2",publicKey:"MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEurX0moghaYJa3/n1W7oYR6IruUxMl1kiA/CYsTDvjrBTLH3mCyE+YvxzZlqDChv5BU6iucWQkVrgHp2WQhbuIQ=="}
#@header-end
#@body-start
param=$1
if [[ "${param}" != "" ]]; then
"${param}"  --help
else
  echo "������Ҫ��ѯ���������ƣ�"
fi
#@body-end