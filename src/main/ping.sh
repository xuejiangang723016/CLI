#@header-start
#@name ping
#@desc 查看网络是否正常
#@input {name:"ping",default:"",type:"String",option:"yes",detail:"服务器IP"}
#@output {name:"ping",file:"ping",type:"system",scriptdesc:"系统指令",modifyStat:"false", isModify:"false"}
#@header-end
#@body-start
ping $*
#@body-end