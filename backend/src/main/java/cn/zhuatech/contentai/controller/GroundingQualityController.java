/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contentai.controller;import cn.zhuatech.contentai.common.ApiResponse;import cn.zhuatech.contentai.service.GroundingQualityService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/contentai/insights/grounding-quality") public class GroundingQualityController{private final GroundingQualityService service;/**
                                                                                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                    */
public GroundingQualityController(GroundingQualityService service){this.service=service;}/**
                                                                                                                                                                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                             */
@PostMapping ApiResponse<GroundingQualityService.Result> evaluate(@Valid @RequestBody GroundingQualityService.Request r){return ApiResponse.ok(service.evaluate(r));}}
