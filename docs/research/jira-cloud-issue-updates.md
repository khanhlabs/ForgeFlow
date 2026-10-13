# Jira Cloud: cập nhật work item

Nghiên cứu ngày 12-10-2026. Phạm vi: tài liệu chính thức Atlassian cho Jira Cloud.

## Kết luận

Jira Cloud không dùng `PATCH` cho API sửa work item chính; endpoint là `PUT /rest/api/3/issue/{issueIdOrKey}`. Tuy nhiên, đây là API cập nhật **theo field**, không phải contract được mô tả như thay toàn bộ resource: request có `fields` (gán trực tiếp) và `update` (các thao tác theo field). Tài liệu Cloud hiện không nói nguyên văn rằng field bị bỏ qua sẽ giữ nguyên, nên không nên trích dẫn điều đó như một cam kết Cloud.

## Cách Jira xử lý

- UI hỗ trợ chỉnh từng field: chọn field, nhập giá trị, rồi thay đổi được lưu khi focus rời khỏi field. [Atlassian Support: Update a work item's details](https://support.atlassian.com/jira-software-cloud/docs/update-a-work-items-details/)
- API `PUT` nhận `fields` và `update`; các field được phép sửa được xác định theo user và issue bằng `GET /rest/api/3/issue/{issueIdOrKey}/editmeta`. [Atlassian REST API: Edit issue](https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issues/#api-rest-api-3-issue-issueidorkey-put)
- `update` hỗ trợ thao tác riêng từng field, ví dụ `set` summary hoặc `add`/`remove` labels. Client phải xem `editmeta` vì thao tác hợp lệ khác nhau giữa các field. [Atlassian REST API: Get edit issue metadata](https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issues/#api-rest-api-3-issue-issueidorkey-editmeta-get)
- Đổi status không đi qua Edit issue: Jira dùng `POST /rest/api/3/issue/{issueIdOrKey}/transitions`, vì workflow transition trong generic edit bị bỏ qua. [Atlassian REST API: Transition issue](https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issues/#api-rest-api-3-issue-issueidorkey-transitions-post)

## Áp dụng cho ForgeFlow hiện tại

Không cần sao chép đầy đủ độ phức tạp của Jira. Với form chỉnh sửa task, gửi đầy đủ các field có thể sửa bằng `PUT` là đơn giản và rõ ràng. Nếu sau này có inline edit hoặc kéo-thả status, thêm endpoint cập nhật đúng field đó; khi có workflow thực sự thì tách status thành transition như Jira.
