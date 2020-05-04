package com.cskaoyan.shiro.config;

import com.cskaoyan.bean.user.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: Li Qing
 * @Create: 2020/5/4 11:12
 * @Version: 1.0
 */
public class CustomAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.assertRealmsConfigured();
        Collection<Realm> originRealms = this.getRealms();
        MallToken token = (MallToken) authenticationToken;
        String type = token.getType();
        ArrayList<Realm> realms = new ArrayList<>();
        for (Realm originRealm : originRealms) {

            if (originRealm.getName().toLowerCase().contains(type)) realms.add(originRealm);
        }
        return realms.size() == 1 ? this.doSingleRealmAuthentication(((Realm) realms.iterator().next()), authenticationToken) :
                this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
