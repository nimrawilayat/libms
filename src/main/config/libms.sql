USE [libms]
GO

/****** Object:  Table [dbo].[book]    Script Date: 11/18/2016 1:56:25 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[book](
  [id] [int] IDENTITY(1,1) NOT NULL,
  [title] [varchar](50) NOT NULL,
  [author] [varchar](50) NOT NULL,
  [version] [int] NOT NULL,
 CONSTRAINT [PK_id] PRIMARY KEY CLUSTERED 
(
  [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
insert into book (title, author, version) values ('C++ Primer Plus', 'Mithcell Waite', 1)
insert into book (title, author, version) values ('The C Programming Language', 'Ritche & Kernigan', 1)
insert into book (title, author, version) values ('UML Distilled', 'Jake Author', 1)
insert into book (title, author, version) values ('Node.js', 'Jane Author', 1)
insert into book (title, author, version) values ('Python', 'John Author', 1)
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%