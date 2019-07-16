package cn.ntshare.Blog.scheduleTask;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.dto.BaiduLinkSubmissionDTO;
import cn.ntshare.Blog.pojo.Message;
import cn.ntshare.Blog.service.RabbitMqService;
import cn.ntshare.Blog.util.HttpUtil;
import cn.ntshare.Blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.ntshare.Blog.constant.SystemConstant.REDIS_LOCK_KEY;
import static cn.ntshare.Blog.constant.SystemConstant.REDIS_LOCK_TIME;

/**
 * Created By Seven.wk
 * Description: 定时向百度搜索提交站点最新的链接
 * Created At 2019/02/27
 */
@Component
@Slf4j
public class SubmitLinkTask {

    @Value("${server.port}")
    public String redisLockValue;

    @Autowired
    private RabbitMqService rabbitMqService;

    /**
     * 每天23点30分执行
     */
    @Scheduled(cron = "0 30 23 * * *")
    public void task() {
        if (!RedisUtil.getRedisLock(REDIS_LOCK_KEY, redisLockValue, REDIS_LOCK_TIME)) {
            return;
        }
        List<String> urls = RedisUtil.getList(SystemConstant.INDEX_LINKS);
        if (urls.size() == 0) {
            log.info("无链接需要向百度搜索提交");
        } else {
            BaiduLinkSubmissionDTO result = HttpUtil.baiduLinkSubmission(urls);
            String content = "链接推送成功，成功推送 " + result.getSuccess() + " 条链接";
            rabbitMqService.sendNotice(new Message("百度搜索推送链接", content));
            log.info("链接推送成功，成功推送 {} 条链接", result.getSuccess());
        }
    }

}
