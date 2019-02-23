package SearchLucene;

import Classes.Query;

import java.util.ArrayList;

public class ExtractQuery {

	ArrayList<Query> queries;

	int idx = 0;

	public ExtractQuery() {

		queries = new ArrayList<>();
		Query aQuery = new Query();
		aQuery.SetTopicId("901");
		aQuery.SetQueryContent("subs");
		queries.add(aQuery);
		aQuery = new Query();
		aQuery.SetTopicId("902");
		aQuery.SetQueryContent("tree");
		queries.add(aQuery);
		aQuery = new Query();
		aQuery.SetTopicId("903");
		aQuery.SetQueryContent("stack");
		queries.add(aQuery);
		aQuery = new Query();
		aQuery.SetTopicId("904");
		aQuery.SetQueryContent("queue");
		queries.add(aQuery);
	}

	public boolean hasNext() {
		if (idx == queries.size()) {
			return false;
		} else {
			return true;
		}
	}

	public Query next() {
		return queries.get(idx++);
	}

}
