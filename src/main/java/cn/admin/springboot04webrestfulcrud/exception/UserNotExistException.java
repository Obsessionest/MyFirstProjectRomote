package cn.admin.springboot04webrestfulcrud.exception;

/**
 * @author Wang
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户名不存在！");
    }
}