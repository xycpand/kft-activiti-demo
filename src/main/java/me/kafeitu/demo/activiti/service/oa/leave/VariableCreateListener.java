package me.kafeitu.demo.activiti.service.oa.leave;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiVariableEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 引擎的全局监听器
 * @author: Henry Yan
 */
@Service
@Transactional
public class VariableCreateListener implements ActivitiEventListener {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onEvent(ActivitiEvent event) {
        switch (event.getType()) {
            case VARIABLE_CREATED:
                ActivitiVariableEvent variableEvent = (ActivitiVariableEvent) event;
                System.out.println("创建了变量: " + variableEvent.getVariableName() + ", 值：" + variableEvent.getVariableValue());
                break;
            case JOB_EXECUTION_SUCCESS:
                System.out.println("A job well done!");
                break;
            case JOB_EXECUTION_FAILURE:
                System.out.println("A job has failed...");
                break;
            default:
                System.out.println("Event received: " + event.getType());
        }
    }
    
    /**
     * 决定当事件分发时，onEvent(..)方法抛出异常时的行为。 
     * 这里返回的是false，会忽略异常。
     *  当返回true时，异常不会忽略，继续向上传播，迅速导致当前命令失败。 
     *  当事件是一个API调用的一部分时（或其他事务性操作，比如job执行）， 事务就会回滚。
     *  当事件监听器中的行为不是业务性时，建议返回false
     */
    @Override
    public boolean isFailOnException() {
        return false;
    }
}
