/************************************************************************
 * 描述 ：数据库表t_auth_common_token对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yaic.auth.thirdparty.model.AuthTokenModel;


/** 
* @ClassName: AuthTokenDto 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:52:29 
*  
*/
public class AuthTokenDto extends AuthTokenModel {

    private static final long serialVersionUID = AuthTokenDto.class.getName().hashCode();
    
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
    
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
