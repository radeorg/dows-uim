#!/bin/bash
source ./cicd.env
echo "当前路径: $(pwd)"
# sh xxx.sh 项目地址 分支 触发人 触发邮箱 变更文件 提交信息 提交SHA 状态 描述
#项目名
PROJECT_NAME='用户身份[dows-uim]'
#项目地址
PROJECT_URL=$0
#分支或标签
BRANCH_NAME=$1
#触发人
ACTOR_NAME=$2
#触发邮箱
ACTOR_MAIL=$3
#变更文件
CHANGED_FILES=$4
#提交信息
COMMIT_MSG=$5
#提交SHA
COMMIT_SHA=$6
#ACTION状态
ACTIONS_STATUS=$7

#打印信息
echo "变更文件: ${changedFileList}"
echo "代码检测: ${SONARQUBE_HOST}/dashboard?branch=${BRANCH_NAME}&id=${SONARQUBE_KEY}"
echo "all params: ${PROJECT_URL} ,${BRANCH_NAME}, ${ACTOR_NAME},${ACTOR_MAIL},${CHANGED_FILES},${COMMIT_MSG},${COMMIT_SHA},${ACTIONS_STATUS}"

# 状态颜色和文字设置
if [ "$ACTIONS_STATUS" = "green" ]; then
    COLOR="green"
    STATE="部署成功 ✅"
else
    COLOR="red"
    STATE="部署失败 ❌"
fi

#变更文件换行处理
#CFL=$(echo "$CHANGED_FILES" | sed 's/ /\\n/g')
# 处理变更文件换行显示（兼容含空格文件名）
changedFileList=$(echo "$CHANGED_FILES" | tr ' ' '\n' | sed 's/^/> - /')
#组装URL
project_commit_url="${PROJECT_URL}/-/commit/${COMMIT_SHA}"
#sonarqube
sonarqube_branch_url="${SONARQUBE_HOST}/dashboard?branch=${BRANCH_NAME}&id=${SONARQUBE_KEY}"



title='应用发布'
time="$(date "+%Y-%m-%d")"
times="$(date "+%H:%M:%S")"
xingqi="$(date "+%A")"
public_ip=$(curl -s https://api.ipify.org)
#runner 中需要apt-get install -y ifconfig
ip=$(ifconfig | grep inet | awk 'NR==3{print $2}')
lsblk=$(df -h / | awk '{print $4"/"$2 , $5}' | tail -n 1 )
mem_info=$(free -h) # 运行 free -h 命令并将结果保存到变量 mem_info 中
total_memory=$(echo "$mem_info" | grep "Mem:" | awk '{print $2}') # 从输出结果中提取第二列（Total）的值作为总内存大小
mem=$(free | grep Mem | awk '{print $3/$2 * 100.0}')
cpu=$(top -b -n1 | grep "Cpu(s)" | awk '{print $2}')

# 系统信息采集
#time="$(date "+%Y-%m-%d")"
#times="$(date "+%H:%M:%S")"
#xingqi="$(date "+%A")"
#public_ip=$(curl -s https://api.ipify.org || echo "N/A")
#ip=$(hostname -I | awk '{print $1}' || echo "N/A")
#disk_info=$(df -h / | awk 'NR==2{print $4"/"$2 " ("$5")"}')
#mem_info=$(free -m | awk 'NR==2{printf "%.1fG/%.1fG (%.0f%%)", $3/1024, $2/1024, $3/$2*100}')
#cpu_usage=$(top -bn1 | grep "Cpu(s)" | sed "s/.*, *\([0-9.]*\)%* id.*/\1/" | awk '{print 100 - $1"%"}')


#sh /radeorg/bole/webhook.sh && 'radeorg/dows-uim.git' 'refs/heads/sit-1.0.250313' 'geeker-lait' 'lait.zhang@gmail.com' '.github/workflows/maven.yml' 'test' 'd65f755ad2a524194e6b8fc41f6cb52f13d3d0f6' 'green'

curl $WEBHOOK_DING_TALK \
-H 'Content-Type: application/json' \
-d '{
     "msgtype": "markdown",
     "markdown": {
         "title":"项目名",
         "text": "应用发布<font color='"$COLOR"'>'"$STATE"'</font>\n
           发布时间: <font color=\"comment\">'"$time $times $xingqi"'</font>
           项目名称: <font color=\"comment\">'"$PROJECT_NAME"'</font>
           项目仓库: <font color=\"comment\">'"$PROJECT_URL"'</font>
           项目分支: <font color=\"comment\">'"$BRANCH_NAME"'</font>
           触发账号: <font color=\"comment\">'"$ACTOR_NAME"'</font>
           触发邮箱: <font color=\"comment\">'"$ACTOR_MAIL"'</font>
           HOST: <font color=\"comment\">'"$public_ip:$ip"'</font>
           DISK: <font color=\"comment\">'"$lsblk"'</font>
           MEM: <font color=\"comment\">'"$total_memory,$mem%"'</font>
           CPU: <font color=\"comment\">'"$cpu%"'</font>
           COMMIT-ID: <font color=\"comment\">'"$COMMIT_SHA"'</font>
           提交说明: <font color=\"comment\">'"$COMMIT_MSG"'</font>
           变更文件:
           <font color=\"comment\">'"$CHANGED_FILES"'</font>"
     }
 }'




#
#
##weixin
#curl --location ${WEBHOOK_DING_TALK} \
#--header 'Content-Type: application/json' \
#--data '
#{
#  "msgtype": "markdown",
#  "markdown": {
#    "content": "开发环境\n
#      >发布时间: <font color=\"comment\">'"$time $times $xingqi"'</font>
#      >项目名: <font color=\"comment\">'"$PROJECT_NAME"'</font>
#      >分支名: <font color=\"comment\">'"$BRANCH_NAME"'</font>
#      >触发者: <font color=\"comment\">'"$AUTHOR_NAME"'</font>
#      >状态: <font color='"$COLOR"'>'"$STATE"'</font>
#      >HOST: <font color=\"comment\">'"$public_ip:$ip"'</font>
#      >DISK: <font color=\"comment\">'"$lsblk"'</font>
#      >MEM: <font color=\"comment\">'"$total_memory,$mem%"'</font>
#      >CPU: <font color=\"comment\">'"$cpu%"'</font>
#      >描述: <font color='"$COLOR"'>'"$COMMIT"'</font>
#      >变更文件:
#<font color=\"comment\">'"$changedFileList"'</font>"
#  }
#}'
##http://192.168.23.19/shdy/shdy-task/-/commit/6b9fa816dd1e5bfeae1aba0bdf4329a31d017fc1
#
##feishu
##curl -X POST -H "Content-Type: application/json" \
##        -d '{
##          "msg_type":"post",
##          "content": {
##            "post": {
##              "zh_cn": {
##                "title": "构建'"$PROJECT_NAME"'",
##                "content": [
##                  [
##                    {"tag": "text", "text": "'"时间：$time $times $xingqi\n"'"},
##                    {"tag": "text", "color": "'"$COLOR"'", "text": "'"状态：$STATE_DESCR\n"'"},
##                    {"tag": "text", "text": "'"作者：$GITLAB_USER_NAME: "'"},
##                    {"tag": "a", "href": "'"$CI_COMMIT_AUTHOR"'", "text": "'"$CI_COMMIT_AUTHOR\n"'"},
##                    {"tag": "text", "text": "'"项目：$PROJECT_NAME\n"'"},
##                    {"tag": "text", "text": "'"分支："'"},
##                    {"tag": "a", "href": "'"$MODULE_URL"'", "text": "'"$CI_COMMIT_REF_NAME\n"'"},
##                    {"tag": "text", "text": "'"COMMIT_SHA："'"},
##                    {"tag": "a", "href": "'"${gitlab_commit_url}"'", "text": "'"$CI_COMMIT_SHA\n"'"},
##                    {"tag": "text", "text": "'"SONARQUBE："'"},
##                    {"tag": "a", "href": "'"${sonarqube_branch_url}"'", "text": "http://192.168.111.103:9001\n"},
##                    {"tag": "text", "text": "'"HOST:$public_ip:$ip\n"'"},
##                    {"tag": "text", "text": "'"DISK:$lsblk\n"'"},
##                    {"tag": "text", "text": "'"MEM:$total_memory,$mem%\n"'"},
##                    {"tag": "text", "text": "'"CPU:$cpu%\n"'"},
##                    {"tag": "text", "text": "'"描述：$CI_COMMIT_MESSAGE\n"'"},
##                    {"tag": "text", "text": "'"清单：\n"'"},
##                    {"tag": "text", "text": "'"$CFL"'"}
##                  ]
##                ]
##              }
##            }
##          }
##        }' "${WEBHOOK_FEI_SHU}"