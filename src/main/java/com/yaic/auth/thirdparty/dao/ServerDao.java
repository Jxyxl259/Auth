package com.yaic.auth.thirdparty.dao;

import java.util.List;

import com.yaic.auth.thirdparty.model.ServerModel;

public interface ServerDao {
    int deleteByPrimaryKey ( Integer serverId );

    int insert ( ServerModel serverModel );

    int insertSelective ( ServerModel serverModel );

    ServerModel selectOneByPrimaryKey ( Integer serverId );

    int updateByPrimaryKeySelective ( ServerModel serverModel );

    int updateByPrimaryKey ( ServerModel serverModel );
    
    List<ServerModel> selectByConditions(ServerModel serverModel);
    
    ServerModel selectOneByServerUrl(String serverUrl);
    
    List<ServerModel> selectAllView(ServerModel serverModel);
}