package com.ventas.comput.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.dao.FacturaDAO;
import com.ventas.comput.models.Factura;
import com.ventas.comput.security.jwt.JWTFilter;
import com.ventas.comput.service.FacturaService;
import com.ventas.comput.utils.ComputoUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class FacturaServiceImpl implements FacturaService{

	
	
	@Autowired
	JWTFilter jwtFilter;
	
	@Autowired
	FacturaDAO facturaDao;
	
	
	
	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
	
		
		
		try {
			
			String fileName;
			
			if(validateRequestMap(requestMap)) {
				
				if(requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
					fileName= (String) requestMap.get("uuid");
					
					
					
				}else {
					fileName= ComputoUtils.getUUID();
					
					requestMap.put("uuid", fileName);
						
					insertFactura(requestMap);
					
				}
				
				String data= "Name: " + requestMap.get("nombre") + "\n" + "Telefono: "+requestMap.get("telefono")+
						
						"\n"+ "Email: " +requestMap.get("email")+ "\n" + "MetodoPago: "+requestMap.get("metodoPago");
				
				Document document= new  Document();
				
				PdfWriter.getInstance(document, new FileOutputStream(ComputoConstans.STORE_LOCATION+"\\"+fileName+".pdf"));
				
				document.open();
				
				setRectangle(document);
				
				
				
				Paragraph chunk= new Paragraph("Gestion Sistema Computo",getFont("Header"));
				chunk.setAlignment(Element.ALIGN_CENTER);
				document.add(chunk);
				
				Paragraph paragraph = new Paragraph(data+"\n \n",getFont("Data"));
				
				document.add(paragraph);
		
				PdfPTable table  = new PdfPTable(6);
				
				table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				table.setWidthPercentage(100);
				
				addTableHeader(table);
				JSONArray jsonArray= ComputoUtils.getJsonArrayFromString((String) requestMap.get("detalleProducto"));
						
				for(int i=0 ; i<jsonArray.length();i++) {
					
					addRows(table,ComputoUtils.getMapFromJson(jsonArray.getString(i)));
					
				}
				
				document.add(table);
				
				Paragraph footer= new Paragraph("Total:  "+requestMap.get("total")+ "\n"
						+"Gracias por su compra",getFont("Data"));
				
				document.add(footer);
				
				document.close();
				
				return new ResponseEntity<String>("{\"uuid\":\""+fileName+"\"}",HttpStatus.OK);
				
				
		
			}
			
			return ComputoUtils.getResponseEntity("Data no correcta", HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	private void addRows(PdfPTable table, Map<String, Object> data) {
		
		
		  Image image = null;

		    try {
		        image = Image.getInstance((String) data.get("image"));
		        image.scaleToFit(50, 50); // Ajusta el tamaño de la imagen según sea necesario
		        PdfPCell imageCell = new PdfPCell(image);
		        imageCell.setBorder(Rectangle.NO_BORDER); // Elimina el borde de la celda de imagen
		        table.addCell(imageCell);
		        
		        
				
				
			    table.addCell((String) data.get("nombre"));
			    table.addCell((String) data.get("categoria_nombre"));
			    table.addCell(String.valueOf(data.get("cantidad"))); // Convertir a String directamente
			    table.addCell(String.valueOf(data.get("precio"))); // Convertir a String directamente
		        
		        
		    } catch (BadElementException | IOException e) {
		        e.printStackTrace(); // Maneja errores al cargar la imagen
		        table.addCell("N/A"); // Agrega "N/A" si hay un error al cargar la imagen
		    }
		
		

	    
	    // Realizar la operación si ambos valores son números
	    Object precio = data.get("precio");
	    Object cantidad = data.get("cantidad");

	    if (precio instanceof Number && cantidad instanceof Number) {
	        double total = ((Number) precio).doubleValue() * ((Number) cantidad).doubleValue();
	        table.addCell(String.valueOf(total));
	    } else {
	        // Manejar el caso en el que uno o ambos valores no sean números
	        table.addCell("N/A");
	    }
	}

	private void addTableHeader(PdfPTable table) {
	
		Stream.of("Imagen","Nombre","Categoria","Cantidad","Precio","Sub Total")
		.forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(0);
			header.setPhrase(new Phrase(columnTitle));
			
			
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(header);
			
		
			
		}
		
				);
		
		
	}

	private void setRectangle(Document document) throws DocumentException{

		Rectangle rect= new Rectangle(577,825,18,15);
		
		rect.enableBorderSide(1);
		
		rect.enableBorderSide(2);
		
		rect.enableBorderSide(4);
		
		rect.enableBorderSide(8);
		
		rect.setBorderColor(BaseColor.WHITE);
		
		rect.setBorderWidth(0);
		
		document.add(rect);

		

		
		
	}

	private Font getFont(String type) {
	
	switch (type) {
	case "Header":
		Font headerFont=FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE,18,BaseColor.BLACK);
		return headerFont;
		
	case "Data":	
		Font dataFont= FontFactory.getFont(FontFactory.TIMES_ROMAN,11,BaseColor.BLACK);
		dataFont.setStyle(Font.BOLD);
		return dataFont;
	default:
return new Font();
	}
		
	}

	private void insertFactura(Map<String, Object> requestMap) {

		
		try {
			
			Factura factura= new Factura();
			
			factura.setUuid((String) requestMap.get("uuid"));

			factura.setNombre((String) requestMap.get("nombre"));
			
			factura.setEmail((String)requestMap.get("email"));
			
			factura.setTelefono((String)requestMap.get("telefono"));
			
			factura.setMetodoPago((String)requestMap.get("metodoPago"));
			
			factura.setTotal((Integer)requestMap.get("total"));

			
			factura.setDetalleProducto((String) requestMap.get("detalleProducto"));
			
			factura.setCreate(jwtFilter.getCurrentUser());
			
			facturaDao.save(factura);
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
	}

	private boolean validateRequestMap(Map<String, Object> requestMap) {
		
		return requestMap.containsKey("nombre") &&
				requestMap.containsKey("telefono") && 
				requestMap.containsKey("email") && 
				requestMap.containsKey("metodoPago") && 
				requestMap.containsKey("detalleProducto") && 
				requestMap.containsKey("total");
		
	}

	@Override
	public ResponseEntity<List<Factura>> getFacturas() {
		List<Factura> list=new ArrayList<>();
		
	
			

			
			if(jwtFilter.isAdmin()) {
				
				list =facturaDao.getAllFacturas();
				
			}else {
				list=facturaDao.getFacturaPorUsuario(jwtFilter.getCurrentUser());	
			}
			
			
			
	
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<byte[]> getPdf(Map<String, Object> reqMap) {
	
		try {
			
			
			byte[] byteArray=new byte[0];
			
			if(!reqMap.containsKey("uuid") && validateRequestMap(reqMap)	) {
				
				return new ResponseEntity<byte[]>(byteArray,HttpStatus.BAD_REQUEST);
				
			
				
			}
			
			String filePath= ComputoConstans.STORE_LOCATION +"\\"+(String) reqMap.get("uuid")+".pdf";
			
			if(ComputoUtils.isFileExist(filePath)) {
				byteArray = getByteArray(filePath);
				return new ResponseEntity<byte[]>(byteArray,HttpStatus.OK);
			}else {
				reqMap.put("isGenerate", false);
				
				generateReport(reqMap);
				
				byteArray=getByteArray(filePath);
				
				return new ResponseEntity<byte[]>(byteArray,HttpStatus.OK);
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private byte[] getByteArray(String filePath) throws Exception{
		
		File initialFile= new File (filePath);
		
		FileInputStream targetStream = new FileInputStream(initialFile);
		
		byte[] byteArray  = IOUtils.toByteArray(targetStream);
 		
		targetStream.close();
		
		return byteArray;
		
		
	}

	@Override
	public ResponseEntity<String> deleteFactura(Integer id) {
	
		try {
			
			Optional optional= facturaDao.findById(id);
			
			if(!optional.isEmpty()) {
				
				facturaDao.deleteById(id);
				
				return ComputoUtils.getResponseEntity("Factura eliminada con exito", HttpStatus.OK);
				
			}
			
			
			return ComputoUtils.getResponseEntity("No existe el id de la factura", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
	}

}
