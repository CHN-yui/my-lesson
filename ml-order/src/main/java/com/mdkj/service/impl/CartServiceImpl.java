package com.mdkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdkj.domain.Cart;
import com.mdkj.service.CartService;
import com.mdkj.mapper.CartMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.mdkj.util.NotNullCheckUtil;

/**
* 针对表【cart(购物车表)】的数据库操作Service实现
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService{

    @Override
    public List<Cart> selectAll() {
        LambdaQueryWrapper <Cart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Cart::getDeleted,0);
        return list(lqw);
    }


    @Override
    public List<Cart> selectList(Cart iCart) {
        LambdaQueryWrapper <Cart> lqw = this.lqw(iCart);
        lqw.eq(Cart::getDeleted,0);
        return list(lqw);
    }

    @Override
    public void insert(Cart iCart) {
        save(iCart);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        ArrayList<Cart> list = new ArrayList<>();
        for (String id : ids.split(",")) {
            Cart entity  = new Cart();
            entity.setId(Long.valueOf(id));
            entity.setDeleted(1);
            list.add(entity);
        }
        updateBatchById(list);
    }

    @Override
    public void update(Cart iCart) {
        updateById(iCart);
    }


    @Override
    public IPage<Cart> pageList(Cart iCart, Integer page, Integer size) {
        Page<Cart> pag = new Page<>(page,size);
        LambdaQueryWrapper<Cart> lqw = lqw(iCart);
        lqw.eq(Cart::getDeleted,0);
        return page(pag,lqw);
    }

    @Override
    public List<Cart> getExcelData() {
        return selectAll();
    }

    public LambdaQueryWrapper<Cart> lqw(Cart iCart){
        LambdaQueryWrapper <Cart> lqw = new LambdaQueryWrapper<>();
        if(NotNullCheckUtil.checkNotNull(iCart.getId())){
            lqw.eq(Cart::getId,iCart.getId());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getFkUserId())){
            lqw.eq(Cart::getFkUserId,iCart.getFkUserId());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getUsername())){
            lqw.eq(Cart::getUsername,iCart.getUsername());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getFkCourseId())){
            lqw.eq(Cart::getFkCourseId,iCart.getFkCourseId());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getCourseTitle())){
            lqw.eq(Cart::getCourseTitle,iCart.getCourseTitle());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getCourseCover())){
            lqw.eq(Cart::getCourseCover,iCart.getCourseCover());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getCoursePrice())){
            lqw.eq(Cart::getCoursePrice,iCart.getCoursePrice());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getVersion())){
            lqw.eq(Cart::getVersion,iCart.getVersion());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getDeleted())){
            lqw.eq(Cart::getDeleted,iCart.getDeleted());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getCreated())){
            lqw.eq(Cart::getCreated,iCart.getCreated());
        };
        if(NotNullCheckUtil.checkNotNull(iCart.getUpdated())){
            lqw.eq(Cart::getUpdated,iCart.getUpdated());
        };
        return lqw;
    }
}




