package top.vchao.examine.db;

import top.vchao.examine.MyApplication;
import top.vchao.examine.bean.QuestBean;
import top.vchao.examine.bean.QuestBeanDao;

/**
 * @ 创建时间: 2017/6/11 on 9:48.
 * @ 描述：
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class LoveDao {
    /**
     * 添加数据
     *
     * @param questBean
     */
    public static void insertLove(QuestBean questBean) {
        MyApplication.getDaoInstant().getQuestBeanDao().insertOrReplace(questBean);
    }

    /**
     * 删除数据
     *
     * @param id
     */
//    public static void deleteLove(long id) {
//        MyApplication.getDaoInstant().getQuestBeanDao().deleteByKey(id);
//    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateLove(QuestBean shop) {
        MyApplication.getDaoInstant().getQuestBeanDao().update(shop);
    }

    /**
     * 查询条件为id=id的数据
     *
     * @return
     */
    public static QuestBean queryLove(long id) {

        return MyApplication.getDaoInstant().getQuestBeanDao().queryBuilder().where(QuestBeanDao.Properties.Id.eq(id)).list().get(0);
    }

}
