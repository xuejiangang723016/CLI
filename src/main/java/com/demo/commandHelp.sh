#@header-start
#@name commandHelp
#@desc 获取命令帮助
#@input {name:"commandName",default:"",type:"String",option:"yes",detail:"命令名称"}
#@output {name:"closedLink",file:"closedLink"}
#@auth {authcode:"test2|MEQCIJewjdA1WVD7p/CdM10zDan4JYglVaI+2DcunFmvUMgOAiC1lAhpg64cRWSbD063sPpY3hA3p15EFD+E1cjhWSnzhw==",alg:"SM3withSM2",publicKey:"MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEurX0moghaYJa3/n1W7oYR6IruUxMl1kiA/CYsTDvjrBTLH3mCyE+YvxzZlqDChv5BU6iucWQkVrgHp2WQhbuIQ=="}
#@header-end
#@body-start
param=$1
if [[ "${param}" != "" ]]; then
"${param}"  --help
else
  echo "请输入要查询的命令名称！"
fi
#@body-end