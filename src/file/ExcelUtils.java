package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opencsv.CSVReader;

import jnetwork.Graph;
import jnetwork.SimpleWeightedEdge;

public class ExcelUtils {
	/**
	 * Write a graph to an excel file. Output file must be of format xls.
	 * 
	 * @param graph
	 * @param url
	 */
	public static <T> void writeGraph(Graph<T, ? extends Excelable> graph, String url) {
		String[] header = null;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();

		for (Excelable e : graph.edges()) {
			header = e.header();
			break;
		}

		int cellnum = 0;
		int rownum = 0;

		HSSFRow row = sheet.createRow(rownum++);
		row.createCell(cellnum++).setCellValue("begin");
		row.createCell(cellnum++).setCellValue("end");
		for (String col : header) {
			row.createCell(cellnum++).setCellValue(col);
		}

		for (Graph.Entry<T, ? extends Excelable> e : graph.entrySet()) {
			cellnum = 0;
			T begin = e.getBegin();
			T end = e.getEnd();
			row = sheet.createRow(rownum++);
			row.createCell(cellnum++).setCellValue(begin.toString());
			row.createCell(cellnum++).setCellValue(end.toString());
			float[] attrs = e.getLink().items();
			for (float attr : attrs) {
				row.createCell(cellnum++).setCellValue(attr);
			}
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(url);
			workbook.write(out);
			workbook.close();
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Write a set of Excelable edges into an excel file. Output file must be of
	 * format xls. Notice that edges here don't contain begin vertex and end vertex,
	 * so if you need to write down the edges with begin and end vertex, you have to
	 * make sure the edges themselves contain that information.
	 * 
	 * @param edges
	 * @param url
	 */
	public static void writeEdges(Collection<? extends Excelable> edges, String url) {
		String[] header = null;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();

		for (Excelable e : edges) {
			header = e.header();
			break;
		}

		HSSFRow row = sheet.createRow(0);
		int cellnum = 0;
		for (String col : header) {
			row.createCell(cellnum++).setCellValue(col);
		}

		int rownum = 1;
		for (Excelable e : edges) {
			float[] attrs = e.items();
			cellnum = 0;
			row = sheet.createRow(rownum++);
			for (float attr : attrs) {
				row.createCell(cellnum++).setCellValue(attr);
			}
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(url);
			workbook.write(out);
			workbook.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Read graph from a csv file, which is of format begin-end-weight. For example:
	 * -----------------------
	 *  begin 	end 	weight
	 *   1 		2		0.5 
	 *   1 		3 		1.0 
	 *   2 		3 		2.4 
	 *   2 		1 		3.0
	 * -----------------------
	 *  omitLineNumber is the number of the lines which we
	 * don't want to read at the beginning. In the above example, omitLineNumber
	 * should be 1.
	 * 
	 * @param url
	 * @param omitLineNumber
	 * @return
	 */
	public static Graph<String, SimpleWeightedEdge> readSimpleCSV(String url, int omitLineNumber) {
		Graph<String, SimpleWeightedEdge> graph = new Graph<String, SimpleWeightedEdge>();
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(new File(url)));
			int n = 0;
			while (n < omitLineNumber) {
				reader.readNext();
			}
			String[] strs = null;
			while ((strs = reader.readNext()) != null) {
				graph.addDiEdge(strs[0], strs[1], new SimpleWeightedEdge(Float.parseFloat(strs[2])));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graph;
	}
}
