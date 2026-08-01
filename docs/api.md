# ContentAI API 摘要

版权所有 © 2026 上海如静知华信息科技有限公司。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录并获取 JWT |
| GET | `/api/admin/dashboard` | 智能内容运营数据 |
| GET | `/api/admin/work-orders` | 内容生产任务清单 |
| GET | `/api/shopfloor/dashboard` | 内容创作者工作台 |
| POST | `/api/shopfloor/work-orders/{id}/reports` | 提交内容审核记录 |
| POST | `/api/shopfloor/ai-preview` | 调用可替换内容 AI Provider |
| POST | `/api/shopfloor/content-review` | 发布前检查隐私、禁用词和事实引用完整性 |
