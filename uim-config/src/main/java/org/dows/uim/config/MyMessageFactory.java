package org.dows.uim.config;

import com.mybatisflex.core.audit.AuditMessage;
import org.apache.logging.log4j.message.MessageFactory;

public class MyMessageFactory implements MessageFactory {

    @Override
    public AuditMessage create() {
        AuditMessage message = new AuditMessage();

        // 在这里
        // 设置 message 的基础内容，包括 platform、module、url、user、userIp、hostIp 内容
        // 剩下的 query、queryParams、queryCount、queryTime、elapsedTime 为 mybatis-flex 设置
        //开启审计功能
        message.setAuditEnable(true);

//设置 SQL 审计收集器
        MessageCollector collector = new ConsoleMessageCollector();
        message.setMessageCollector(collector);
        return message;
    }
}