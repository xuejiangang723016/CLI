#@header-start
#@name free
#@desc 查看服务器内存使用情况
#@output {name:"free",file:"free",type:"system",scriptdesc:"系统指令",modifyStat:"false", isModify:"false"}
#@header-end
#@body-start
free $*
#@body-end