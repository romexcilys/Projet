package com.computerdatabase.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLogs is a Querydsl query type for Logs
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLogs extends EntityPathBase<Logs> {

    private static final long serialVersionUID = -333976146L;

    public static final QLogs logs = new QLogs("logs");

    public final DateTimePath<org.joda.time.DateTime> date = createDateTime("date", org.joda.time.DateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idComputer = createNumber("idComputer", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath operation = createString("operation");

    public QLogs(String variable) {
        super(Logs.class, forVariable(variable));
    }

    public QLogs(Path<? extends Logs> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogs(PathMetadata<?> metadata) {
        super(Logs.class, metadata);
    }

}

