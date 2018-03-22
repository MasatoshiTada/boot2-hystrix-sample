package com.example.client2.service;

import com.example.client2.dto.HelloDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService2 {

    private static final Logger logger = LoggerFactory.getLogger(HelloService2.class);

    private final RestTemplate restTemplate;

    public HelloService2(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "execute2Fallback"
            , commandProperties = {
            // タイムアウト時間をミリ秒で設定（デフォルトは1秒）
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            // Openを検討し始めるリクエスト数（デフォルトは20リクエスト）
            , @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
            // Closed -> Openに遷移するエラー発生割合の閾値（デフォルトは50%）
            , @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20")
            // ウィンドウサイズ（デフォルトは10秒）
            , @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000")
            // Open -> Half-Openまでの時間（デフォルトは5秒）
            , @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
    }
    )
    public HelloDto execute2(String prefix) {
        HelloDto helloDto = restTemplate.getForObject(
                "http://producer/api/hello", HelloDto.class);
        helloDto.setMessage(prefix + " : " + helloDto.getMessage());
        return helloDto;
    }

    public HelloDto execute2Fallback(String prefix, Throwable throwable) {
        logger.error("エラー！！！フォールバックしています。 Prefix = " + prefix);
        logger.error(throwable.getMessage());
        HelloDto helloDto = new HelloDto();
        helloDto.setMessage(prefix + " : This is a default message");
        return helloDto;
    }
}
