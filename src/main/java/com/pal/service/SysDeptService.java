package com.pal.service;

import com.pal.dao.SysDeptMapper;
import com.pal.exception.ParamException;
import com.pal.model.SysDept;
import com.pal.param.DeptParam;
import com.pal.util.BeanValidator;
import com.pal.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一级部门名称重复");
        }

        SysDept sysDept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();

        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        sysDept.setOperator("system"); //TODO
        sysDept.setOperateIp(""); //TODO
        sysDept.setOperateTime(new Date());
        sysDeptMapper.insert(sysDept);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        //TODO
        return true;
    }

    private String getLevel(Integer deptId) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (sysDept == null) {
            return null;
        }
        return sysDept.getLevel();
    }
}
