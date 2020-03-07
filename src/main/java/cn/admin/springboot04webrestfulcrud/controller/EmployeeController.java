package cn.admin.springboot04webrestfulcrud.controller;

import cn.admin.springboot04webrestfulcrud.dao.EmployeeDao;
import cn.admin.springboot04webrestfulcrud.dao.DepartmentDao;
import cn.admin.springboot04webrestfulcrud.entities.Department;
import cn.admin.springboot04webrestfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Wang
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired(required = false)
    DepartmentDao departmentDao;

    /**
     * 查询所有员工，返回列表页面
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        //thymeleaf默认会拼串
        return "/emp/list";
    }

    /**
     * 跳转到员工添加页面
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面，显示所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "/emp/add";
    }

    /**
     * 添加员工
     * SpringMVC自动将请求参数和入参对象的属性一一绑定；
     * 要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //保存员工信息
        employeeDao.save(employee);
        //添加操作完成后，重定向到列表页面
        //redirect:表示重定向到一个地址   /表示当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/emps";
    }


    /**
     * 来到修改页面，查出当前员工，在页面回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        //根据id获取即将修改的员工信息
        Employee employee = employeeDao.get(id);
        //把员工信息存入model中，以便显示在页面中
        model.addAttribute("emp",employee);
        //显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面)
        return "/emp/add";
    }

    /**
     * 员工修改;需要提交员工ID
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 员工删除
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
