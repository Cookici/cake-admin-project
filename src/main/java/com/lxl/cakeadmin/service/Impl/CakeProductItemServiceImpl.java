package com.lxl.cakeadmin.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.cakeadmin.entity.CakeProductItem;
import com.lxl.cakeadmin.mapper.CakeProductItemMapper;
import com.lxl.cakeadmin.service.CakeProductItemService;
@Service
public class CakeProductItemServiceImpl extends ServiceImpl<CakeProductItemMapper, CakeProductItem> implements CakeProductItemService{

}
