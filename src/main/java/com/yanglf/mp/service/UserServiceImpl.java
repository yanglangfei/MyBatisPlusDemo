package com.yanglf.mp.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanglf.mp.mapper.UserMapper;
import com.yanglf.mp.model.User;
import org.springframework.stereotype.Service;

/**
 * @author yanglf
 * @sine 2020.02.09
 * @descriptipon
 * @see
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
