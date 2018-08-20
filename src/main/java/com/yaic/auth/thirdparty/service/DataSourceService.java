package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.dto.DataSourceViewDto;
import com.yaic.auth.thirdparty.model.DataSourceModel;


/** 
* @ClassName: DataSourceService 
* @Description: 渠道接口
* @author 
* @date 2018年6月17日 下午9:59:59 
*  
*/
public interface DataSourceService {


	/** 
	* @Title: getList 
	* @Description: 查询所有末分页
	* @param dataSourceModel
	* @return    
	* @return List<DataSourceModel>  
	* @throws 
	*/
	public List<DataSourceModel> getList(DataSourceModel dataSourceModel) throws Exception;

	/** 
	* @Title: getOneByDataSource 
	* @Description: 查出渠道List,取第一个
	* @param dataSourceModel
	* @return    
	* @return DataSourceModel  
	* @throws 
	*/
	public DataSourceModel getOneByDataSource(DataSourceModel dataSourceModel) throws Exception;

	/** 
	* @Title: getOneByDataSourceCode 
	* @Description: 根据dataSourceCode精确查询对象
	* @param dataSourceCode
	* @return    
	* @return DataSourceModel  
	* @throws 
	*/
	public DataSourceModel getOneByDataSourceCode(String dataSourceCode) throws Exception;

	/** 
	* @Title: getOneByDataSourceId 
	* @Description: 根据dataSourceId精确查询对象
	* @param dataSourceId
	* @return    
	* @return DataSourceModel  
	* @throws 
	*/
	public DataSourceModel getOneByDataSourceId(Integer dataSourceId) throws Exception;

	/** 
	* @Title: addInfo 
	* @Description: 新增记录
	* @param dataSourceModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer addInfo(DataSourceModel dataSourceModel) throws Exception;

	
	/** 
	* @Title: updateInfo 
	* @Description: 修改记录信息
	* @param dataSourceModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer updateInfo(DataSourceModel dataSourceModel) throws Exception;

	/** 
	* @Title: deleteInfo 
	* @Description: 根据主键编号删除数据
	* @param dataSourceId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfo(Integer dataSourceId) throws Exception;
	
	/** 
	* @Title: deleteInfoByIds 
	* @Description: 批量删除
	* @param ids
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfoByIds(List<String> ids) throws Exception;
	
	/** 
	* @Title: getDataSourceView
	* @Description: 根据前台传过来dataSourceModel获取DataSourceViewDto，主要负责前台展示
	* @param dataSourceModel
	* @return    
	* @return List<DataSourceViewDto>
	* @throws 
	*/
	public List<DataSourceViewDto> getDataSourceView(DataSourceModel dataSourceModel) throws Exception;

}
