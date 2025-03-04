# Player 2 Server Access

## Overview

This mod allows Official Player2 AI Friends to bypass Minecraft authentication while keeping your server in online mode. 
This means you can have AI friends join your secure server without compromising the authentication of your server.


## Requirements

- Minecraft (latest version)
- [Fabric Mod Loader](https://fabricmc.net/use/installer/)
- [Fabric API](https://modrinth.com/mod/fabric-api)


## Important Notes
- Make sure the following line is set in your `server.properties` file:
   ```
   enable-secure-profile=false
   ```
- This mod **only** works with servers in online mode
- The mod does **not** work with offline/cracked servers
- Only Player2 AI Friends are supported.

## Technical explanation of how it works
The mod works by adding a listener for a custom authentication packet. The packet is sent by the AI Friend to any server where the version sent in the SLP contains `(Player 2 Supported)`

The packet contains the following information:
- Username
- Signed username, using the AI Friend's private key

The mod then checks if the signed name is valid by verifying it with the AI Friend's public key.

## Permissions
Your AI friends will automatically be granted operator permissions when they join the server. This is to ensure that they function as optimally as possible.

## Terms of use
This software is free to use in conjunction with the Player2 service. 

## Licensing
Copyright (c) 2025 Elefant AI Inc.
All rights reserved.
