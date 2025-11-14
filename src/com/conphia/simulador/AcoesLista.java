
public abstract class AcoesLista {

	
	public static String sorteioSQL(int numero) {
		switch(numero) {
			case 0:
				return "2025-11-12T20:01:05Z host-web-01 app-waf INFO  [WAF_RULE_403] Request blocked: SQL injection pattern detected in parameter 'username'. Rule id=9102, action=BLOCK, client_ip=203.0.113.45";
			case 1:
				return "2025-11-12T20:01:07Z host-web-01 app-server WARN  [INPUT_SANITIZE] Suspicious payload sanitized for session=8f3a..; payload logged to /var/log/security/sql_suspects.log";
			case 2:
				return "2025-11-12T20:01:10Z host-fw-01 firewall INFO  [IP_BLOCK] Added client_ip=203.0.113.45 to blacklist for 24h via automation rule 'block-sqli-ips";
			case 3:
				return "2025-11-12T20:01:07Z host-web-01 app-server WARN  [INPUT_SANITIZE] Suspicious payload sanitized for session=8f3a..; payload logged to /var/log/security/sql_suspects.log";
			case 4:
				return "2025-11-12T20:01:20Z soc-01 analyst INFO  [IOC_UPLOAD] Indicators from event appended to SIEM and WAF signatures; alert_thresholds tightened for /login endpoints";
			default:
				return "Era pra isso acontecer não";
		}
	}
	
	public static String sorteioSSH(int numero) {
		switch(numero) {
			case 0:
				return "2025-11-12T21:05:42Z bastion-01 sshd WARN  [AUTH_FAIL] Failed password for invalid user 'admin' from 198.51.100.132 (attempt 12)";
			case 1:
				return "2025-11-12T21:05:43Z bastion-01 authdaemon INFO  [RATE_LIMIT] Automated rate-limit triggered for source 198.51.100.132 — subsequent connections delayed";
			case 2:
				return "2025-11-12T21:05:44Z bastion-01 authdaemon INFO  [IP_BLOCK] Source 198.51.100.132 blacklisted by fail2ban (jail=ssh, ban_time=86400s)";
			case 3:
				return "2025-11-12T21:05:46Z idm-01 iam INFO  [MFA_ENFORCE] MFA requirement enforced for user 'ops' after detecting brute-force attempts; pending users notified";
			case 4:
				return "2025-11-12T21:06:00Z soc-01 analyst INFO  [CRED_ROTATE] Alert: rotating keys for all privileged accounts that used password auth; new keys issued and old keys revoked";
			default:
				return "Era pra isso acontecer não";
		}
	}
	public static String sorteioDataExfiltration(int numero) {
		switch(numero) {
			case 0:
				return "2025-11-12T22:12:11Z proxy-01 dap INFO  [DLP_ALERT] Large archive upload attempt detected to external host upload.example.net — file_size=4.2GB, rule=CONFIDENTIAL_DATA";
			case 1:
				return "2025-11-12T22:12:12Z proxy-01 dap WARN  [TRANSFER_BLOCK] Outbound transfer blocked by DLP policy 'block-confidential-uploads'; user=j.silva@empresa.local, session=7c9d..";
			case 2:
				return "2025-11-12T22:12:15Z nas-01 storage INFO  [ACCESS_AUDIT] Unusual file read pattern logged for share \\\\srv\\finance\\Q4 — user=j.silva, bytes_read=4.2GB, timeframe=00:03:00";
			case 3:
				return "2025-11-12T22:12:18Z soc-01 analyst INFO  [CONTAINMENT] Endpoint 'workstation-204' isolated from network via NAC; forensic collection in progress";
			case 4:
				return "2025-11-12T22:12:30Z secops-01 mgr INFO  [NOTIFICATION] Regulatory/legal team notified; temporary external interface keys revoked; incident ticket INC-20251112-045 opened"
		;
			default:
				return "Era pra isso acontecer não";
		}
	}
	public static String sorteioEmail(int numero) {
		switch(numero) {
			case 0:
				return "2025-11-12T10:43:00Z mail-gw-01 antispam INFO  [PHISH_QUARANTINE] Incoming message flagged as phishing (suspicious sender, display-name spoofing). Message moved to quarantine 						id=Q-99823; recipient=all@finance.local";
			case 1:
				return "2025-11-12T10:43:05Z mail-gw-01 dmarc INFO  [SPF_DKIM_FAIL] SPF/DKIM/DMARC checks failed for sender 'accounts@payportal.com' — verdict=reject";
			case 2:
				return "2025-11-12T10:43:10Z sec-awareness INFO  [USER_WARN] Automated phishing alert sent to recipients with guidance and safe-report link; all clicked-links rewrited via safe-redirect";
			case 3:
				return "2025-11-12T22:12:18Z soc-01 analyst INFO [CONTAINMENT] Endpoint 'workstation-204' isolated from network via NAC; forensic collection in progress";
			case 4:
				return "2025-11-12T10:45:30Z secops-01 infra INFO [TRAINING] Scheduled targeted awareness campaign for finance team (mandatory module assigned) due in 7 days";
			default:
				return "Era pra isso acontecer não";
		}
	}
	public static String sorteioRansomware(int numero) {
		switch(numero) {
			case 0:
				return "2025-11-12T03:20:33Z endpoint-312 edr WARN  [FS_CHANGE_RATE] Rapid file modification detected in \\\\filesrv\\home\\user123 (entropy increase + create/modify spikes) — rule=RANSOM_BEHAVIOR_7";
			case 1:
				return "2025-11-12T03:20:34Z endpoint-312 edr INFO  [PROCESS_KILL] Terminated suspicious process 'svchost_crypt.exe' (pid=5421); process hash=sha256:aaa... logged";
			case 2:
				return "2025-11-12T03:20:35Z nac-01 network INFO  [ISOLATE_HOST] Host endpoint-312 placed in quarantine VLAN via NAC automation; all SMB and Internet access revoked";
			case 3:
				return "2025-11-12T03:20:40Z backup-01 backupmgr INFO  [RESTORE_POINT] Initiated immutable backup restore for fileserver 'filesrv' from snapshot 2025-11-12T02:30:00Z (pre-infection); restore job RB-221 created";
			case 4:
				return "2025-11-12T03:20:50Z forensics-01 analyst INFO  [IOC_SHARE] Hashes and TTPs shared with CERT and internal IR team; affected accounts locked and password reset forced";
			case 5:
				return "2025-11-12T03:21:00Z secops-01 mgr INFO  [POST_INCIDENT] Deployed kernel/EDR rule updates, applied OS patches to vulnerable hosts, and scheduled full tabletop review (INC-20251112-031)";
			default:
				return "Era pra isso acontecer não";
		}
	}
}
