#@header-start
#@name ping
#@desc �鿴�����Ƿ�����
#@input {name:"ping",default:"",type:"String",option:"yes",detail:"������IP"}
#@output {name:"ping",file:"ping",type:"system",scriptdesc:"ϵͳָ��",modifyStat:"false", isModify:"false"}
#@header-end
#@body-start
ping $*
#@body-end