package com.lxl.cakeadmin.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.cakeadmin.mapper.CakeItemMapper;
import com.lxl.cakeadmin.entity.CakeItem;
import com.lxl.cakeadmin.service.CakeItemService;
@Service
public class CakeItemServiceImpl extends ServiceImpl<CakeItemMapper, CakeItem> implements CakeItemService{

}
