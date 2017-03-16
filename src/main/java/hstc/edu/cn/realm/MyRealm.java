package hstc.edu.cn.realm;

import hstc.edu.cn.po.Blogger;
import hstc.edu.cn.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Description �Զ���realm
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private BloggerService bloggerService;

	/**
	 * Ϊ��ǰ��½���û������ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// ������˲�����Ŀ��û����һ��ģ���Ϊ��һ���û�
		return null;
	}

	/**
	 * ��ǰ��½���û�����������֤
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal(); // ��ȡ�û���
		Blogger blogger = bloggerService.getByUsername(username); // �����û��������ݿ��в�ѯ��������Ϣ

		if (blogger != null) {
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);//�ѵ�ǰ�û��浽session��
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
					blogger.getUsername(), blogger.getPassword(), "MyRealm");
			return authcInfo;
		} else {
			return null;
		}

	}

}