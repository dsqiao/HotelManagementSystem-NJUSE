import Cookies from 'js-cookie'

const TokenKey = 'NJUSE-TOKEN'

export function getToken() {
    return Cookies.get(TokenKey)
}

// 创建 name 为 'NJUSE-TOKEN'，value 为 token 的 Cookie
export function setToken(token) {
    return Cookies.set(TokenKey, token)
}

export function removeToken() {
    return Cookies.remove(TokenKey)
}
