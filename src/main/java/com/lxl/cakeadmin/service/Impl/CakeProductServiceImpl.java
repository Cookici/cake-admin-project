package com.lxl.cakeadmin.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.cakeadmin.mapper.CakeProductMapper;
import com.lxl.cakeadmin.entity.CakeProduct;
import com.lxl.cakeadmin.service.CakeProductService;
@Service
public class CakeProductServiceImpl extends ServiceImpl<CakeProductMapper, CakeProduct> implements CakeProductService{

}
