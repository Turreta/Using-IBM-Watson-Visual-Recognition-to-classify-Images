package com.turreta.watson.visualrecognition.turretawatsonvisualrecognition;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.nio.file.Paths;

@SpringBootApplication
public class TurretaWatsonVisualRecognitionApplication
{

		public static void main(String[] args) throws Exception {
			ApplicationContext context = SpringApplication.run(TurretaWatsonVisualRecognitionApplication.class, args);

			WatsonVisualRecognitionService service = context.getBean(WatsonVisualRecognitionService.class);

			train(service);

			// classify(service);
		}

	private static void train(WatsonVisualRecognitionService service) throws Exception
	{
		// Upload zip files from resources directory
		CreateClassifierOptions createClassifierOptions = new CreateClassifierOptions.Builder()
				.name("malaysia-food")
				.addClass("nasilemak",
						Paths.get(
								ClassLoader.getSystemResource("nasi-lemak_postive_examples.zip").toURI())
								.toFile())
				.negativeExamples(
						Paths.get(
								ClassLoader.getSystemResource("negative_examples.zip").toURI())
								.toFile())
				.build();

		service.trainService(createClassifierOptions);
	}

	private static void classify(WatsonVisualRecognitionService service) throws Exception
	{
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(
				Paths.get(
						ClassLoader.getSystemResource("nasi-lemak-typical-indonesian-food-bali-27670807.jpg")
								.toURI())
						.toFile())
				.parameters("{\"classifier_ids\":[ \"malaysiaxfood_287949534\"]}").build();

		ClassifiedImages classifiedImages = service.classify(classifyOptions);

		System.out.println(classifiedImages);
	}
}
