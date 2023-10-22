# simple-web-chat - API

[![my-app-workflow](https://github.com/mmeowmeow/simple-web-chat/actions/workflows/app-workflow.yml/badge.svg)](https://github.com/mmeowmeow/simple-web-chat/actions/workflows/app-workflow.yml)

## Installation

Can be install jar (from Packages page) or as a docker image: `docker pull ghcr.io/mmeowmeow/simple-web-chat:latest`

## Run locally

`java -jar path/to/jar`<br>

or<br>

`docker compose up -d` from app directory

## List REST API endpoints

http://localhost:8080/swagger-ui/index.html

## List WS endpoints

- Connection - http://localhost:8080/ws
- Group topic - http://localhost:8080/topic/messages (send to http://localhost:8080/app/chat)
- Private - http://localhost:8080/user/userName/private (send to http://localhost:8080/app/private-chat)
