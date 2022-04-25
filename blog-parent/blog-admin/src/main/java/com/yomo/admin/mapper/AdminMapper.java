package com.yomo.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yomo.admin.pojo.Admin;
import com.yomo.admin.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    List<Permission> findPermissionByAdminId(Long adminId);
}
