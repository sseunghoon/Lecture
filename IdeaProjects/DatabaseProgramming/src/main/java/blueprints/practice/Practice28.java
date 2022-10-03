package blueprints.practice;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Practice28 {

	public static void main(String[] args) throws IOException {

		Graph g = new TinkerGraph();

		BufferedReader r = new BufferedReader(new FileReader("c:\\Users\\Sejong\\Email-EuAll.txt"));

		while (true) {
			String line = r.readLine();
			if (line == null)
				break;

			if (line.startsWith("#"))
				continue;

			String[] arr = line.split("\t");

			String senderID = arr[0];
			String receiverID = arr[1];

			Vertex senderV = g.getVertex(senderID);
			if (senderV == null) {
				senderV = g.addVertex(senderID);
			}

			Vertex receiverV = g.getVertex(receiverID);
			if (receiverV == null) {
				receiverV = g.addVertex(receiverID);
			}

			g.addEdge(senderID + "|send|" + receiverID, senderV, receiverV, "send");
		}

		g.getVertices().forEach(v -> {
			System.out.println(v);

			v.getVertices(Direction.IN).forEach(in -> {
				in.getVertices(Direction.IN).forEach(in2 -> {
					System.out.println("\t" + in2);
				});
			});
		});

		r.close();
	}

}