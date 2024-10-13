---
title: "Home Lab"
created: 2024-10-04
---

{toc.placeholder}

# Intro

This post is more like a notebook for setting up the home lab server.

# Networking

## Firewall

### Setting correct default zone

When listing all zones:
```shell
firewall-cmd --list-all-zones
```
the `public` default zone is not connected to `enp2s0` in `firewall-cmd` so it is useful to change it to a correct 
(`FedoraServer`) one: 
```shell
firewall-cmd --set-default-zone=FedoraServer
```

### Allowing denied logs to appear in `journalctl`

* Change line `LogDenied=off` to `LogDenied=all` in `vi /etc/firewalld/firewalld.conf` file.

* Restart service: `systemctl restart firewalld`

* View rejected request logs (last 20 records): `journalctl --lines=20 --grep='REJECT'` or in follow mode:
  `journalctl -f --grep='REJECT'`

### Adding Wireguard service to allow VPN connection

```shell
firewall-cmd --add-service=wireguard
```

Add other conf options, so that it will look like:

```text
FedoraServer (default, active)
  target: default
  ingress-priority: 0
  egress-priority: 0
  icmp-block-inversion: no
  interfaces: enp2s0
  sources: 
  services: wireguard
  ports: 
  protocols: 
  forward: yes
  masquerade: yes
  forward-ports: 
  source-ports: 
  icmp-blocks: 
  rich rules: 

home (active)
  target: ACCEPT
  ingress-priority: 0
  egress-priority: 0
  icmp-block-inversion: no
  interfaces: wg0
  sources: 
  services: ssh
  ports: 
  protocols: 
  forward: yes
  masquerade: no
  forward-ports: 
  source-ports: 
  icmp-blocks: 
  rich rules: 
```

### Adding `/etc/wireguard/wg0.conf` config file

* Save the following content
 
```shell
[Interface]
Address = 10.0.0.1/24
PrivateKey = <home_lab_private_key>
ListenPort = 51820
MTU = 1280

[Peer]
PublicKey = <first_device_pub_key>
AllowedIPs = 10.0.0.2/32

[Peer]
PublicKey = <second_device_pub_key>
AllowedIPs = 10.0.0.3/32
```

* add new interface to Wireguard: 

```shell
wg-quick up wg0
```

* create persistent service:

```shell
wg-quick down wg0
systemctl enable wg-quick@wg0
systemctl start wg-quick@wg0
```

* to connect from the client install client and add connection with the following conf:
```shell
[Interface]
PrivateKey = <first_device_private_key>
ListenPort = 51820
Address = 10.0.0.3/32
DNS = 10.0.0.1

[Peer]
PublicKey = <home_lab_public_key>
AllowedIPs = 0.0.0.0/1, 128.0.0.0/1
Endpoint = homelab.pswidersk.com:51820

```

# Docker

Exporting existing containers to compose format:
```shell
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock ghcr.io/red5d/docker-autocompose $(docker ps -aq) > compose.yaml
```

