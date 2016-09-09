package com.server.java.handler;

import java.lang.reflect.Method;
import java.util.List;

import com.server.java.CmdHandler;
import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;

public class DDZPlayHandler extends CmdHandler {

	@Override
	public void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList) {
		switch (msgEntity.getCmdCode()) {// 根据命令码对应找到对应处理方法
		case CmdConstant.NAME_CHECK:
			break;
		case CmdConstant.SAY_HELLO:
			break;
		default:
			System.out.println("找不到对应的命令码");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int add(int param1, int param2) {   
		       return param1 + param2;   
		    }   
		    
		    public String echo(String msg) {   
		        return "echo: " + msg;   
		    } 
	
	public static void main(String[] args)throws Exception{
		Class<?> classType = DDZPlayHandler.class;   
		    Object invokeTester = classType.newInstance();   
		    
		        // Object invokeTester = classType.getConstructor(new   
		        // Class[]{}).newInstance(new Object[]{});   
		    
		    
		        //获取InvokeTester类的add()方法   
		        Method addMethod = classType.getMethod("add", new Class[]{int.class, int.class});   
		        //调用invokeTester对象上的add()方法   
		        Object result = addMethod.invoke(invokeTester, new Object[]{new Integer(100), new Integer(200)});   
		        System.out.println((Integer) result);   
		    
		    
		        //获取InvokeTester类的echo()方法   
		        Method echoMethod = classType.getMethod("echo", new Class[]{String.class});   
		        //调用invokeTester对象的echo()方法   
		        result = echoMethod.invoke(invokeTester, new Object[]{"Hello"});   
		        System.out.println((String) result);   

	}

}
