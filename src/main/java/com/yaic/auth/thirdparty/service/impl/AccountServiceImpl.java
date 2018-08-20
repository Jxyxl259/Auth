package com.yaic.auth.thirdparty.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.thirdparty.dao.AccountDao;
import com.yaic.auth.thirdparty.dao.AuthEncryptDao;
import com.yaic.auth.thirdparty.dao.AuthMappingDao;
import com.yaic.auth.thirdparty.dao.CallbackUrlDao;
import com.yaic.auth.thirdparty.dao.DataSourceDao;
import com.yaic.auth.thirdparty.dao.ProjectDao;
import com.yaic.auth.thirdparty.model.AccountModel;
import com.yaic.auth.thirdparty.model.DataSourceModel;
import com.yaic.auth.thirdparty.model.ProjectModel;
import com.yaic.auth.thirdparty.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceServiceimpl.class);

	@Autowired
	private AccountDao accountMapper;
	@Autowired
	private DataSourceDao dataSourceMapper;
	@Autowired
	private ProjectDao projectMapper;
	@Autowired
	private AuthMappingDao authMappingMapper;
	@Autowired
	private CallbackUrlDao callbackUrlMapper;
	@Autowired
	private AuthEncryptDao authEncryptMapper;

	@Override
	public List<AccountModel> getList(AccountModel accountModel) throws Exception {
		return accountMapper.getLists(accountModel);
	}

	@Override
	public AccountModel getOneByAccount(AccountModel accountModel) throws Exception{

		List<AccountModel> list = this.getList(accountModel);

		if (list.size() > 0) {
			logger.debug("if list.size > 0 ,get the first record");
			return list.get(0);
		}
		return null;
	}

	@Override
	public AccountModel getOneByAppid(String appId) throws Exception {
		return accountMapper.getOneByAppid(appId);
	}

	@Override
	public AccountModel getOneByAppCode(String appCode)  throws Exception{
		return accountMapper.getOneByAppCode(appCode);
	}

	@Override
	public AccountModel getOneByAccountId(Integer accountId)  throws Exception{
		return accountMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public Integer addInfo(AccountModel accountModel) throws Exception {
//		accountModel.setCreatedUser(Constants.LOGIN_USER_CODE());
//		accountModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		accountModel.setValidFlag(PublicParamters.VALID_FLAG_Y);
		int resu = accountMapper.insertSelective(accountModel);
		return resu;
	}

	@Override
	public Integer updateInfo(AccountModel accountModel)  throws Exception{
//		accountModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		int resu = accountMapper.updateByPrimaryKeySelective(accountModel);
		return resu;
	}

	@Override
	public Integer deleteInfo(Integer accountId) throws Exception {
		int result = 0;
		
		// 1.根据accountId查出所有，得到accountModel
		AccountModel accountModel = accountMapper.selectByPrimaryKey(accountId);
		
		if (accountModel != null) {
			
			logger.info("delete account by accountId:{},accountName{}", accountModel.getAccountId(),accountModel.getAccountName());
			// 2.根据appId查出所有渠道信息，得到多dataSourceId
			List<DataSourceModel> dataSourcelists = dataSourceMapper.getLists(new DataSourceModel(accountModel.getAppId()));
			logger.debug("account:{},dataSourcelists.size{}",accountModel.getAccountName(), dataSourcelists.size());
			
			if (dataSourcelists.size() > 0) {
				
				// 3.根据dataSourceId查出所有方案，得到projectId,authId
				for (DataSourceModel dataSourceIdTemp : dataSourcelists) {
					
					List<ProjectModel> projectLists = projectMapper.getList(new ProjectModel(dataSourceIdTemp.getDataSourceId()));
					logger.debug("datasource:{},projectLists.size:{}",dataSourceIdTemp.getSourceName(), projectLists.size());
					
					if (projectLists.size() > 0) {
						
						// 4.更新删除
						for (ProjectModel model : projectLists) {
							
							callbackUrlMapper.deleteByProjectId(model.getProjectId());
							authMappingMapper.deleteByProjectId(model.getProjectId());
							
							if(model.getAuthId() != null)
								authEncryptMapper.deleteByPrimaryKey(model.getAuthId());
							
							projectMapper.deleteByPrimaryKey(model.getProjectId());
							logger.debug("delete callbackUrl,authMapping,authEncrypt,project by projectId{}",model.getProjectId());
						}
					}
					dataSourceMapper.deleteByPrimaryKey(dataSourceIdTemp.getDataSourceId());
				}
			}
			int i = accountMapper.deleteByPrimaryKey(accountId);
			if (i > 0)
				result = i;
			logger.info("accountModel 删除成功");
		}else{
			
			logger.info("delete account by accountId:{},accountModel is null",accountId);
		}
		return result;
	}

	@Override
	public Integer deleteInfoByIds(List<String> ids) throws Exception {
		int result = 0;
		if (ids.size() > 0) {
			for (String accountIdTemp : ids) {
				Integer accountId = Integer.valueOf(accountIdTemp);
				result = this.deleteInfo(accountId);
			}
		}
		return result;
	}
}
