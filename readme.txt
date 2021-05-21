nginx配置：
location / {
			proxy_pass   http://127.0.0.1:9528; #转发地址
            proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr; #保留代理之前的真实客户端ip
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for; #记录代理过程

        }