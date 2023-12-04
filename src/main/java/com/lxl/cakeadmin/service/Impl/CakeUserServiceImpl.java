package com.lxl.cakeadmin.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.cakeadmin.mapper.CakeUserMapper;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.service.CakeUserService;
@Service
public class CakeUserServiceImpl extends ServiceImpl<CakeUserMapper, CakeUser> implements CakeUserService{

}
