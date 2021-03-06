package com.instagram.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.app.config.FileConfig;
import com.instagram.app.domain.profile.ProfileImg;
import com.instagram.app.domain.profile.ProfileRepository;
import com.instagram.app.domain.user.User;
import com.instagram.app.domain.user.UserRepository;
import com.instagram.app.web.dto.account.AccountResponseDto;
import com.instagram.app.web.dto.account.AccountUpdateImgReqDto;
import com.instagram.app.web.dto.account.AccountUpdateReqDto;
import com.instagram.app.web.dto.account.PasswordUpdateRepDto;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public AccountResponseDto getAccountProfile(int usercode) {
		return profileRepository.getAccountProfileByUsercode(usercode).toDto();
	}
	
	@Override
	public boolean updateAccount(AccountUpdateReqDto accountUpdateReqDto) {
		if(userRepository.checkUsername(accountUpdateReqDto.getUsername()) != 0) {
			return false;
		}
		profileRepository.updateUserMst(accountUpdateReqDto.toEntity());
		profileRepository.updateUserDtl(accountUpdateReqDto.toEntity());
		return true;
	}
	
	@Override
	public boolean updatePassword(User user, PasswordUpdateRepDto passwordUpdateRepDto) {
		user.setPassword(BCrypt.hashpw(passwordUpdateRepDto.getNewPassword(), BCrypt.gensalt()));
		return profileRepository.updatePassword(user) != 0;
	}
	
	@Override
	public boolean updateProfileImg(User user, AccountUpdateImgReqDto accountUpdateImgReqDto) {
		if(accountUpdateImgReqDto.getFile() != null) {
			String imageFileName = accountUpdateImgReqDto.getFile().getOriginalFilename();
			String tempImageFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + imageFileName;
			String path = FileConfig.profileImgPath;
			Path imgeFilePath = Paths.get(path + "/" + tempImageFileName);
			
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			try {
				Files.write(imgeFilePath, accountUpdateImgReqDto.getFile().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ProfileImg profileImg = ProfileImg.builder()
					.usercode(user.getUsercode())
					.img_url(tempImageFileName)
					.build();
			
			return profileRepository.updateProfileImg(profileImg) != 0;
		}
		return false;
	}
}





