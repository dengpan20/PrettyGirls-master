package coder.prettygirls.data.bean.picbean;

import java.util.List;

/**
 * Created by dengpan on 17/6/21.
 */

public class ResponseData {

    /**
     * message : 成功
     * code : 0
     * data : {"shopList":[{"shop_id":"cc99fa16-fbed-4192-b172-c211af86d077","shop_name":"???1","shop_url":"http://v.yupoo.com/photos/888080/collections/","shop_contact":"1328925178-821051753"},{"shop_id":"e872a49d-1942-443f-862e-ae16133bf4fe","shop_name":"???1","shop_url":"http://v.yupoo.com/photos/888080/collections/","shop_contact":"1328925178-821051753"},{"shop_id":"784a1c39-aecb-474a-9d09-6f9344e2e8d5","shop_name":"???1","shop_url":"http://v.yupoo.com/photos/888080/collections/","shop_contact":"1328925178-821051753"},{"shop_id":"aad30a3e-6edd-418f-a8b3-9f8f0c2204ef","shop_name":"???1","shop_url":"http://v.yupoo.com/photos/888080/collections/","shop_contact":"1328925178-821051753"},{"shop_id":"a115b0aa-d911-43bf-a20f-a41701302a28","shop_name":"??333","shop_url":"","shop_contact":"1328925178-821051753"},{"shop_id":"0a433055-9779-4945-8d8e-31f79a38b312","shop_name":"??333","shop_url":"http://v.yupoo.com/photos/zhonghe0594/collections/","shop_contact":"1328925178-821051753"},{"shop_id":"0442550e-dadd-4d8b-9865-d54b7ac8f831","shop_name":"??333","shop_url":"http://v.yupoo.com/photos/zhonghe0594/collections/","shop_contact":"1328925178-821051753"},{"shop_id":"f0cf73bc-ea1e-4068-8434-db271ef81db4","shop_name":"pan","shop_url":"http://www.baidu.com","shop_contact":"134190611111"}]}
     */

    private String message;
    private int code;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shop_id : cc99fa16-fbed-4192-b172-c211af86d077
         * shop_name : ???1
         * shop_url : http://v.yupoo.com/photos/888080/collections/
         * shop_contact : 1328925178-821051753
         */

        private List<Shop> shopList;

        public List<Shop> getShopList() {
            return shopList;
        }

        public void setShopList(List<Shop> shopList) {
            this.shopList = shopList;
        }

    }
}
