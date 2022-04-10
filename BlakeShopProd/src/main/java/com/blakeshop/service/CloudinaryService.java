package com.blakeshop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	//Declaramos una variable de tipo cloudinary que inicialiará este servicio y se le pasará el map con los valores respectivos.
	Cloudinary cloudinary;
	private Map<String, String> valuesMap = new HashMap<>();
	
	//Rellenamos el map e inicializamos la variable de cloudinary.
	public CloudinaryService() {
		valuesMap.put("cloud_name", "dietzstj8");
		
		valuesMap.put("api_key","592756587656289");
		
		valuesMap.put("api_secret", "x35DBZSQanfhADjkzOLDv4HcHz4");
		
		cloudinary = new Cloudinary(valuesMap);
	}
	
	// Convertimos el archivo y lo subimos a cloudinary, después lo borramos para que no ocupe memoria y retornamos la respuesta 
	// de cloudinary.
	public Map upload(MultipartFile multipartfile)throws IOException{
		File file = convert(multipartfile);
		
		Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		
		file.delete();
		
		return result;
		
	}
	
	//Convertimos un listado de archivos y los subimos a cloudinary
	public List<Map> uploadMultipleFiles(List<MultipartFile> multipartfiles)throws IOException{
		List<Map> results = new ArrayList();
		
		for(MultipartFile multipartfile:multipartfiles) {
			File file = convert(multipartfile);
			results.add(cloudinary.uploader().upload(file, ObjectUtils.emptyMap()));
			file.delete();
		}
		
		return results;
	}
	
	//Destruimos el archivo del repositorio en la nube de cloudinary
	public Map delete(String id)throws IOException {
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		
		return result;
	}
	
	//Convertimos el archivo multiparfile en un archivo legible para subir a cloudinary
	public File convert(MultipartFile multpartfile) throws IOException{
		File file = new File(multpartfile.getOriginalFilename());
		
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multpartfile.getBytes());
		fo.close();
		
		return file;
		
	}
}
