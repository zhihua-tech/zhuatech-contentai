/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contentai.controller;import cn.zhuatech.contentai.common.ApiResponse;import cn.zhuatech.contentai.service.ContentPublicationGovernanceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController@RequestMapping("/api/enterprise/contentai")public class ContentPublicationGovernanceController{private final ContentPublicationGovernanceService service;/**
                                                                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                          */
public ContentPublicationGovernanceController(ContentPublicationGovernanceService s){service=s;}/**
                                                                                                                                                                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                          */
@PostMapping("/publication-governance")public ApiResponse<ContentPublicationGovernanceService.Result>evaluate(@Valid@RequestBody ContentPublicationGovernanceService.Request r){return ApiResponse.ok("内容发布治理完成",service.evaluate(r));}}
