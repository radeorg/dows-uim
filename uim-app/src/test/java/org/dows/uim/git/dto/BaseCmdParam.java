package org.dows.uim.git.dto;

import lombok.Data;
import org.dows.uim.git.CmdParam;

/*@NoArgsConstructor
@AllArgsConstructor*/
@Data
public class BaseCmdParam implements CmdParam {
    int index;
    String sourceBranch;
    String targetBranch;
}
