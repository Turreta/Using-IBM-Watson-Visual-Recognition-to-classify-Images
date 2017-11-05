package com.turreta.watson.visualrecognition.turretawatsonvisualrecognition;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class WatsonVisualRecognitionService
{
	private VisualRecognition visualRecognition;

	@PostConstruct
	public void initService()
	{
		if(visualRecognition == null)
		{
			// Default endpoint is https://gateway-a.watsonplatform.net/visual-recognition/api
			visualRecognition = new VisualRecognition(
					VisualRecognition.VERSION_DATE_2016_05_20,
					"d948f9300c552d0cabf2213518fe8f60693a47c9");
		}
	}

	public void trainService(CreateClassifierOptions createClassifierOptions) throws Exception
	{
		visualRecognition.createClassifier(createClassifierOptions).execute();
	}

	public ClassifiedImages classify(ClassifyOptions classifyOptions) throws Exception
	{
		return visualRecognition.classify(classifyOptions).execute();
	}
}
