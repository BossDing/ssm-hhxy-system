package cn.hqisgood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hqisgood.bean.Msg;
import cn.hqisgood.service.ManagerService;

@Controller
public class ManagerController {
	@Autowired
	ManagerService managerService;
	/**
	 * 查询管理员分页查询
	 * @return
	 */
	
//	@RequestMapping(value="/managers")
	public String getManager(@RequestParam(value="pn", defaultValue="1")Integer pn, Model model) {
		PageHelper.startPage(pn, 5);
		List managers = managerService.getAll();
		PageInfo pageInfo = new PageInfo(managers, 3);
		model.addAttribute("pageInfo", pageInfo);

		return "list_m";
	}
	
	@RequestMapping(value="/managers")
	@ResponseBody
	public Msg getManagerWithJson(@RequestParam(value="pn", defaultValue="1")Integer pn) {
		PageHelper.startPage(pn, 5);
		List managers = managerService.getAll();
		PageInfo pageInfo = new PageInfo(managers, 3);
		return Msg.success().add("pageInfo", pageInfo);
	}
}